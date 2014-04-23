echo "starting ...."
mv target/storeManagement-1.0.0-SNAPSHOT.war storeManagement.war

echo "sending war file..."
scp storeManagement.war storemanager@184.82.177.77:/home/storemanager

echo "sending you... bye"
ssh storemanager@stmanager.com.br


