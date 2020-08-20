# PdfSammenligner - Pdf Diff
Testverktøy for automatisk visuell regresjonstesting av pdfer generert gjennom f.eks journalpostapi

## Om
Tar i mot pdf på rest-grensesnitt. Enten gjennom et grensesnitt tilsvarende journalpostapi 
eller gjennom et eget api for mer kontroll

* Programmet bruker S3 bucket for å lagre baseline av pdf. 
* Finnes ikke pdfen fra før blir den automatisk lastet opp
* Sett ny baseline gjennom AWS-CLI:
`aws s3 cp <nøkkel>* s3://<bucket> `


## Oppsett
* Opprett en ny S3 bucket og legg bucketnavn i properties-filen  
* Legg AWS secrets i `~/.aws/secret` eller som miljøvariabler
* Last ned AWS-CLI for å enkelt sette ny baseline  
