package com.u1145h.kavitaandroid.feature.home

/** JavaScript injected into the embedded Kavita web UI. */
object KavitaJs {

    /** Name of the `@JavascriptInterface` object exposed to the page. */
    const val OBJECT_NAME = "KavitaAndroid"

    /**
     * Injected at DOCUMENT_START: exposes download interception so native
     * DownloadManager handles `/api/Download/` requests instead of the WebView
     * rendering raw binary.
     */
    const val INIT = """
        (function() {
            if (window.KavitaAndroid) {
                var api = window.KavitaAndroid;
                var realFetch = window.fetch;
                window.fetch = function(url, opts) {
                    var u = (typeof url === 'string') ? url : ((url && url.url) || String(url));
                    if (u.indexOf('/api/Download/') !== -1) {
                        api.download(u, '', '');
                        return Promise.resolve(new Response(null, { status: 200, statusText: 'OK' }));
                    }
                    return realFetch.apply(this, arguments);
                };
                var realOpen = XMLHttpRequest.prototype.open;
                XMLHttpRequest.prototype.open = function(method, url) {
                    var u = String(url);
                    if (u.indexOf('/api/Download/') !== -1) {
                        var self = this;
                        api.download(u, '', '');
                        setTimeout(function() { try { self.abort(); } catch (e) {} }, 0);
                        return;
                    }
                    return realOpen.apply(this, arguments);
                };
            }
        })();
    """

    /**
     * Evaluated after each page load: polls for the Kavita auth token and
     * reports it to the native side so OkHttp-backed sync can authenticate.
     *
     * TODO(verify): confirm the actual localStorage key Kavita's web client
     * uses and tighten the candidates below.
     */
    const val SESSION_SYNC = """
        (function() {
            var api = window.KavitaAndroid;
            if (!api) return;
            var reported = false;
            function extract() {
                try {
                    var token = localStorage.getItem('kavita_token')
                        || localStorage.getItem('token')
                        || localStorage.getItem('jwt')
                        || '';
                    if (!token && document.cookie) {
                        var m = document.cookie.match(/(?:^|;\s*)token=([^;]+)/);
                        token = m ? m[1] : '';
                    }
                    var username = localStorage.getItem('kavita_username') || '';
                    if (token && !reported) {
                        reported = true;
                        api.onSession(JSON.stringify({ token: token, username: username }));
                    }
                } catch (e) {}
            }
            var n = 0;
            var timer = setInterval(function() {
                extract();
                if (++n >= 120) clearInterval(timer);
            }, 1000);
        })();
    """

    /**
     * Evaluated after each page load: reads the webpage body's computed
     * background color so the native status/navigation bar strip can match it.
     */
    const val BODY_COLOR = """
        (function() {
            var api = window.KavitaAndroid;
            if (!api) return;
            try {
                var b = getComputedStyle(document.body).backgroundColor;
                if (b && b !== 'rgba(0, 0, 0, 0)' && b !== 'transparent') {
                    api.onBodyColor(b);
                }
            } catch (e) {}
        })();
    """
}
