package com.u1145h.kavitaandroid.feature.home

/** JavaScript injected into the embedded Kavita web UI. */
object KavitaJs {

    /** Name of the `@JavascriptInterface` object exposed to the page. */
    const val OBJECT_NAME = "KavitaAndroid"

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
