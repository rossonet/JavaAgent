# Extract the unobfuscated fat jar from Spring Boot
jar xvf input.jar

# Execute ProGuard, as input for ProGuard use
# -injars BOOT-INF/classes/
java -jar proguard.jar @ proguard.conf

# If you also want to obfuscate the libs, add
# -injars BOOT-INF/lib
# Be aware, probably needs filtering, if some but not all libs should be obfuscated. I have not tested, but I believe the Spring Framwork libraries should not be obfuscated because they would require a lot of keep exceptions in the ProGuard configuration.
# If some of the libs should be used for obfuscation in the copy step below **remove** the libs before copying, otherwise the old files remain in the fat jar.

# Copy the old files excluding the classes to a new directory.
# First, the classes are stored here:
mkdir obfuscated/BOOT-INF
mkdir obfuscated/BOOT-INF/classes

# I assume the output is obfuscated-classes.jar
# It has to be extracted in the new output
cp obfuscated-classes.jar obfuscated/BOOT-INF/classes
pushd obfuscated/BOOT-INF/classes
jar xvf obfuscated-classes.jar
rm obfuscated-classes.jar
popd

# Copy the remaining files
boot_directories=(BOOT-INF/lib META-INF org)
for boot_directory in ${boot_directories[@]}; do
    mkdir -p "./obfuscated/$boot_directory"

    copy_command="cp -R ./$boot_directory/* ./obfuscated/$boot_directory/"
    eval $copy_command
done

# Finally, create a new jar
pushd obfuscated
# Be aware: do not use * as selector, the order of the entries in the resulting jar is important!
jar c0mf ./META-INF/MANIFEST.MF input-obfuscated.jar BOOT-INF/classes/ BOOT-INF/lib/ META-INF/ org/
popd

# Now, there is a jar obfuscated/input-obfuscated.jar that is a Spring Boot fat jar whose BOOT-INF/classes directory is obfuscated.
