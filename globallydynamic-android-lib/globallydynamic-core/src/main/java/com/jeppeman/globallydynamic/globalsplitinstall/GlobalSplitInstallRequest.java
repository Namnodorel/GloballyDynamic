package com.jeppeman.globallydynamic.globalsplitinstall;

import android.net.Uri;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * A request specifying what split APK:s to install
 */
public class GlobalSplitInstallRequest {
    private final List<String> moduleNames;
    private final List<Locale> languages;
    private final List<File> apkLocations;

    private GlobalSplitInstallRequest() {
        throw new IllegalStateException("Not allowed");
    }

    private GlobalSplitInstallRequest(List<String> moduleNames, List<Locale> languages, List<File> apkLocations) {
        this.moduleNames = moduleNames;
        this.languages = languages;
        this.apkLocations = apkLocations;
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

    /**
     * Creates a new {@link Builder} for a request
     *
     * @return a new {@link Builder}
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Builder for the request
     */
    public static class Builder {
        private List<String> moduleNames = new LinkedList<String>();
        private List<Locale> languages = new LinkedList<Locale>();
        private final List<File> apkLocations = new LinkedList<>();

        private Builder() {

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
         * Builds a new {@link GlobalSplitInstallRequest} with the modules and languages
         * added to this builder.
         *
         * @return a new {@link GlobalSplitInstallRequest}
         */
        public GlobalSplitInstallRequest build() {
            return new GlobalSplitInstallRequest(moduleNames, languages, apkLocations);
        }
    }
}
