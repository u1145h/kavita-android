package com.u1145h.kavitaandroid.work;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class DownloadImportWorker_Factory {
  private final Provider<DownloadImporter> downloadImporterProvider;

  private DownloadImportWorker_Factory(Provider<DownloadImporter> downloadImporterProvider) {
    this.downloadImporterProvider = downloadImporterProvider;
  }

  public DownloadImportWorker get(Context appContext, WorkerParameters params) {
    return newInstance(appContext, params, downloadImporterProvider.get());
  }

  public static DownloadImportWorker_Factory create(
      Provider<DownloadImporter> downloadImporterProvider) {
    return new DownloadImportWorker_Factory(downloadImporterProvider);
  }

  public static DownloadImportWorker newInstance(Context appContext, WorkerParameters params,
      DownloadImporter downloadImporter) {
    return new DownloadImportWorker(appContext, params, downloadImporter);
  }
}
