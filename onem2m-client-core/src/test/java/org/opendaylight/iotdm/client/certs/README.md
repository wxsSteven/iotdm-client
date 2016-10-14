# IoTDM Java client testing certificates

This directory includes testing KeyStore files and certificates for two CSEs and common TrustStore for clients of these CSEs.
The common TrustStore includes certificate of testing CertificationAuthority (CA) which has been used to sign certificates of both CSEs.

# Example steps of generating certificates

The directory tree used in this example:

    tjanciga@ubuntu:/ws$ tree certs -d
    certs
    ├── incse
    └── mncse

## Creating KeyStore file and certificate of testing CA

1. Change current directory to the certs/.
    `cd certs`
2. Create keystore file with name ca.jks and password caPass including certificate of the testing CA.
    `keytool -genkeypair -keystore ca.jks -alias ca -keypass caPass -storepass caPass -keyalg EC -dname 'C=SK,L=BA,O=Cisco,OU=Dev,CN=caCert' -ext bc:ca:true`
3. Export CA certificate into file called ca.pem.
    `keytool -keystore ca.jks -alias ca -storepass caPass -exportcert -rfc > ca.pem`
    
## Creating KeyStore file and signing certificate of incse

4.	Change current directory to the certs/incse:
    `cd incse/`
5.	Create KeyStore file with name incse.jks, password incsePass including certificate of incse.
    `keytool -genkeypair -keystore incse.jks -alias incse -keypass incsePass -storepass incsePass -keyalg EC -dname 'C=SK,L=BA,O=Cisco,OU=Dev,CN=incseCert' -ext bc:ca:true`
6.	Generate certificate sign request for incse and sign it by the testing CA and store the signed certificate in file incse.pem
    `keytool -storepass incsePass -keystore incse.jks -certreq -alias incse | keytool -storepass caPass -keystore ../ca.jks -gencert -alias ca -ext BC=0 -rfc > incse.pem`
7.	Import certificate of CA into the KeyStore of incse.
    `keytool -keystore incse.jks -storepass incsePass -importcert -alias ca -file ../ca.pem`
8.	Import the signed certificate of incse into the KeyStore of incse.
    `keytool -keystore incse.jks -storepass incsePass -importcert -alias incse -file incse.pem`

## Creating KeyStore file and signing certificate of mncse
These steps are the same as in case of incse (the previous chapter).

9.	Change current directory to the certs/mncse:
    `cd ../mncse`
10.	Create KeyStore file with name mncse.jks, password mncsePass including certificate of mncse.
    `keytool -genkeypair -keystore mncse.jks -alias mncse -keypass mncsePass -storepass mncsePass -keyalg EC -dname 'C=SK,L=BA,O=Cisco,OU=Dev,CN=mncseCert' -ext bc:ca:true`
11.	Generate certificate sign request for mncse and sign it by the testing CA and store the signed certificate in file mncse.pem
    `keytool -storepass mncsePass -keystore mncse.jks -certreq -alias mncse | keytool -storepass caPass -keystore ../ca.jks -gencert -alias ca -ext BC=0 -rfc > mncse.pem`
12.	Import certificate of CA into the KeyStore of mncse.
    `keytool -keystore mncse.jks -storepass mncsePass -importcert -alias ca -file ../ca.pem`
13.	Import the signed certificate of mncse into the KeyStore of mncse.
    `keytool -keystore mncse.jks -storepass mncsePass -importcert -alias mncse -file mncse.pem`

## Creating TrustStore file common for all clients
This TrustStore file can be used by of AEs and CSEs (re-targetting and notifiers) using certificate of CA.

14.	Change directory back to certs/
    `cd ../`
15.	Create TrustStore with name trust.jks, password trustPass and import certificate of testing CA.
    `keytool -keystore trust.jks -storepass trustPass -importcert -alias ca -file ca.pem`
 

# The resulting directory structure with all files

    tjanciga@ubuntu:/ws$ tree certs
    certs
    ├── ca.jks 	(KeyStore of testing CA)
    ├── ca.pem	(Certificate of testing CA)
    ├── incse
    │   		├── incse.jks 	(-alias incse -storepass incsePass)
    │   		└── incse.pem 	(Certificate of incse signed by testing CA)
    ├── mncse
    │   		├── mncse.jks 	(-alias mncse -storepass mncsePass)
    │   		└── mncse.pem	(Certificate of mncse signed by testing CA)
    └── trust.jks (TrustStore common for all clients) (-alias ca -storepass trustPass)

