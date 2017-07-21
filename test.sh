#!/bin/bash
if (./gradlew test 2>test.err >test.log)
then
	echo OK
else
	echo KO
	exit 1
fi
exit 0
