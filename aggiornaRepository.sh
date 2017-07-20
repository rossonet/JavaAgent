#!/bin/bash
# Salva il contenuto del repository in git
#unset DISPLAY
branch=studioBootstrap
#branch=master
unset SSH_ASKPASS
echo "Aggiorno documentazione"
./generaDocumentazione.sh > /dev/null 2>/dev/null
echo "Eseguo i test"
if (./test.sh)
then
	echo "git add ."
	git add .
	echo "git commit -a -m \"$1\""
	git commit -a -m "$1"
	echo "git push -u origin $branch"
	git push -u origin $branch
	exit 0
else
	echo "Test non riusciti!!"
	exit 1
fi
