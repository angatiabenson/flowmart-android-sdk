# Preserve Kotlin metadata to maintain reflection capabilities
-keep class kotlin.Metadata { *; }

# Retain public API classes and their public methods
-keep public class ke.co.banit.flowmartsdk.FlowMartSdk { public *; }

# Suppress warnings for internal packages
-dontwarn ke.co.banit.flowmartsdk.internal.**

# Retain classes annotated with @Keep to prevent obfuscation
-keep @androidx.annotation.Keep class * { *; }


-dontwarn okhttp3.**
-keep class okhttp3.** { *; }
-keep class okio.** { *; }
-keepattributes *Annotation*
