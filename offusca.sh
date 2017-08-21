#!/bin/bash
# Script Proguard per fat jar Spring Boot

#compila il progetto
./compila.sh

# crea directory di lavoro in build
rm -r build/proguard
mkdir -p build/proguard

# estrae il file
unzip build/libs/agenteAr4k-*.jar -d build/proguard/
pushd build/proguard

# elabora con Proguard
java -jar ../../offuscatore/proguard.jar @ ../../proguard.conf -injars BOOT-INF/classes/ -outjars offuscate

popd

pushd build/libs/

# copia il jar originale in zip
cp agenteAr4k-*.jar agenteAr4k.zip

# muove le classi offuscate
rm -r BOOT-INF
mkdir -p BOOT-INF/classes
mv ../proguard/offuscate/* BOOT-INF/classes/

# cancella le vecchie classi e le ricarica
zip -d agenteAr4k.zip BOOT-INF/classes
zip -ur agenteAr4k.zip BOOT-INF/classes

# rinomina in jar
mv agenteAr4k.zip agenteAr4k.jar

# ripulisce
rm -r BOOT-INF
popd
rm -r build/proguard
exit 0
