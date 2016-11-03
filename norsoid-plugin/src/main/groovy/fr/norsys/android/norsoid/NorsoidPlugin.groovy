package fr.norsys.android.norsoid

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

class NorsoidPlugin implements Plugin<Project> {
    @Override void apply(Project project) {
        def hasApp = project.plugins.withType(AppPlugin)
        def hasLib = project.plugins.withType(LibraryPlugin)

        if (!hasApp && !hasLib) {
            throw new IllegalStateException("'android' or 'android-library' plugin required.")
        }

        project.pluginManager.apply('com.neenbedankt.android-apt')
        project.pluginManager.apply('realm-android')

        project.dependencies {
            compile 'fr.norsys.android.norsoid:norsoid-library:1.0.0'
            apt 'fr.norsys.android.norsoid:norsoid-library:1.0.0'
            apt 'com.google.dagger:dagger-compiler:2.7'
            apt 'com.jakewharton:butterknife-compiler:8.4.0'
        }


    }
}