#!/bin/bash
# by Ambrosini (Rossonet)

# per creare gli elenchi:
#for a in $(ls); do echo -n "$a,"; php -r "print_r(getimagesize('$a'));" | grep width | cut -d\" -f2; done > ../windows.txt

echo "origine ilogo.png"

for elenco in android ios windows wp8
do
	echo "lavoro $elenco"
	cartella=icon/$elenco
	for file in $(cat $elenco.txt)
	do
		nome=$(echo $file | cut -d, -f1)
		dimensione=$(echo $file | cut -d, -f2 | sed 's/\ //g')
		echo "Creo il file $nome di dimensione $dimensione x $dimensione (pixel)"
		#creazione file
		convert -resize ${dimensione}X${dimensione} ilogo.png ${cartella}/${nome}
	done
done
exit 0
