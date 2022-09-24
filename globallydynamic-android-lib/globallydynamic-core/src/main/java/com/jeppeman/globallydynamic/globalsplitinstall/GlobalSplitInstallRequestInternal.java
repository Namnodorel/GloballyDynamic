package com.jeppeman.globallydynamic.globalsplitinstall;

import android.net.Uri;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * A request specifying what split APK:s to install
 */
class GlobalSplitInstallRequestInternal {
    private final List<String> moduleNames;
    private final List<Locale> languages;
    private final List<File> apkLocations;
    private final boolean includeMissing;

    private GlobalSplitInstallRequestInternal() {
        throw new IllegalStateException("Not allowed");
    }

    private GlobalSplitInstallRequestInternal(
            List<String> moduleNames,
            List<Locale> languages,
            List<File> apkLocations,
            boolean includeMissing) {
        this.moduleNames = moduleNames;
        this.languages = languages;
        this.apkLocations = apkLocations;
        this.includeMissing = includeMissing;
    }

    public List<String> getModuleNames() {
        return moduleNames;
    }

    public List<Locale> getLanguages() {
        return languages;
    }

    public List<File> getApkLocations() {
        return apkLocations;
    }

    public boolean shouldIncludeMissingSplits() {
        return includeMissing;
    }

    /**
     * Creates a new {@link Builder} for a request
     *
     * @return a new {@link Builder}
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    public static GlobalSplitInstallRequestInternal fromExternal(GlobalSplitInstallRequest request) {
        GlobalSplitInstallRequestInternal.Builder builder =
                GlobalSplitInstallRequestInternal.newBuilder();

        for (String module : request.getModuleNames()) {
            builder.addModule(module);
        }

        for (Locale language : request.getLanguages()) {
            builder.addLanguage(language);
        }

        for (File apkLocation : request.getApkLocations()) {
            builder.addApkLocation(apkLocation);
        }

        builder.shouldIncludeMissingSplits(false);

        return builder.build();
    }

    /**
     * Builder for the request
     */
    public static class Builder {
        private List<String> moduleNames = new LinkedList<String>();
        private List<Locale> languages = new LinkedList<Locale>();
        private final List<File> apkLocations = new LinkedList<>();
        private boolean includeMissing;

        Builder() {

        }

        /**
         * Adds a module to the request, this must be a valid dynamic feature module name
         *
         * @param moduleName the name of a dynamic feature module
         * @return itself
         */
        public Builder addModule(String moduleName) {
            this.moduleNames.add(moduleName);
            return this;
        }

        /**
         * Adds a language to the request
         *
         * @param locale the language to add to the installation
         * @return itself
         */
        public Builder addLanguage(Locale locale) {
            this.languages.add(locale);
            return this;
        }

        /**
         * Add a location of an APK to be installed
         *
         * @param file the location of the APK to be installed
         * @return itself
         */
        public Builder addApkLocation(File file) {
            this.apkLocations.add(file);
            return this;
        }

        /**
         * Whether or not to include missing splits
         *
         * @param includeMissing whether or not to include missing splits
         * @return itself
         */
        public Builder shouldIncludeMissingSplits(boolean includeMissing) {
            this.includeMissing = includeMissing;
            return this;
        }

        /**
         * Builds a new {@link GlobalSplitInstallRequestInternal} with the modules and languages
         * added to this builder.
         *
         * @return a new {@link GlobalSplitInstallRequestInternal}
         */
        public GlobalSplitInstallRequestInternal build() {
            return new GlobalSplitInstallRequestInternal(moduleNames, languages, apkLocations, includeMissing);
        }
    }
}
