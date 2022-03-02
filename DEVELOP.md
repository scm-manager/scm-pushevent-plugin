## Troubleshooting

> gradle-or-gradlew:17: command not found: gradle

Gradle wurde vermutlich im falschen Verzeichnis ausgeführt. Die `gradlew`-Datei liegt im Order `scm-pushevent-plugin`.
Dort sollte `gradlew <Command>` funktionieren.

> executing gradlew instead of gradle gradle-or-gradlew:15: permission denied: /home/jbartscher/Repositorys/Praxisprojekt/scm-pushevent-plugin/scm-pushevent-plugin/gradlew

Um Gradle bzw. gradlew auszuführen muss die `gradlew`-Datei ausführbar sein. Dies kann mit `chmod +x gradlew`
herbeigeführt werden.

> Trotz Änderungen am Code scheint sich nichts zu verändern, der Server läuft weiter obwohl der Prozess in der IDE bereits gestoppt wurde 

yarn Prozesse stoppen
`ps aux | grep yarn | grep -v grep`
`kill <pid>` 

ScmServer Prozesse stoppen
`jps`
`kill <pid>` 

>   ⃠ Zeichen statt Debugpoints. Debugger geht nicht oder Debug Points werden invalidiert.

Projekt neuimportieren allerdings NUR scm-pushevent-plugin nicht den darüberliegenden Ordner
Debug Konfiguration anlegen:
Neue Konfiguration --> nach Debug suchen --> JVM Debugger --> alle Einstellungen beibehalten

