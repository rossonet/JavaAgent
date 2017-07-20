#!/bin/bash
# by Ambrosini (Rossonet)

# per creare gli elenchi:
#for a in $(ls); do echo -n "$a,"; php -r "print_r(getimagesize('$a'));" | grep width | cut -d\" -f2; done > ../windows.txt

#o

#for elencod in $(ls); do cd $elencod; for a in $(ls); do echo -n "$a,"; php -r "print_r(getimagesize('$a'));" | grep width | cut -d\" -f2; done > ../../${elencod}_screen.txt; cd .. ; done

echo "origine logoRossonet.png"

for elenco in android_screen ios_screen windows_screen wp8_screen
do
	echo "lavoro $elenco"
	cartella=screen/$(echo $elenco | sed 's/_screen//')
	for file in $(cat $elenco.txt)
	do
		nome=$(echo $file | cut -d, -f1)
		dimensione=$(echo $file | cut -d, -f2 | sed 's/\ //g')
		echo "Creo il file $nome di dimensione $dimensione x $dimensione (pixel)"
		#creazione file
		convert -resize $((${dimensione}/5*4))X${dimensione} logoRossonet.png ${cartella}/${nome}_tmp
		convert ${cartella}/${nome}_tmp -gravity center -background white -extent ${dimensione}X${dimensione} ${cartella}/${nome}
		rm ${cartella}/${nome}_tmp
	done
done
exit 0
