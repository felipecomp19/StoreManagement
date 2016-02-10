echo "starting ...."
mv target/storeManagement-1.0.0-SNAPSHOT.war storeManagement-1.0.1-morana.war

echo "sending war file..."
scp storeManagement-1.0.1-morana.war stmanager@stmanager.com.br:/home/stmanager

echo "sending you... bye"