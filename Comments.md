#Gebruik dit bestand om elkaars werk te becommentariëren#


@all met het juiste git commande is de wijziging van Taco te draaien. met -> java -cp ".;../resources" game.BoterKaasEieren <- zet je het classpath naar de juiste resource folder. 
voor het geval het toch niet zou werken heb ik deen failsave ingebouwd. maw vinden we de file niet dan gebruiken we hem ook niet.


@TacoBoerkamp heeft de hele master-branch overschreven met zijn geluid-optie. Je moet natuurlijk eerst je locale directory mergen met de bestaand remote repo voordat je gaat pushen. Verder staat het pad naar het geluidsbestand hard in de code, staan er nog erg veel TODO's in en is de classe Sound.java helemaal uitgecommentarieerd. 
@TacoBoerkamp heeft ook een hele eclipse-omgeving ingecheckt. Al die bestanden (.project, .classpath, ...) moeten niet in de repository terechtkomen. Let erop dat je je .gitignore goed instelt. Zie ook de presentatie en het stukje tekst van Tim op Blackboard.


