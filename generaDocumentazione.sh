#!/bin/bash
echo "Gradle groovydoc javadoc"
./gradlew groovydoc javadoc
echo "Genera il sito con MkDoc"
rm -rf docs
cp -LR templateMkDocs docs
mkdocs build -v -c -d site
rm -rf docs
mv site docs
exit 0
