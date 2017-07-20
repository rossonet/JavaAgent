#!/bin/bash
if (./gradlew test 2>/dev/null >/dev/null)
then
	echo OK
else
	echo KO
	exit 1
fi
exit 0
