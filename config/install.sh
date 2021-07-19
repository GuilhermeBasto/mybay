Version_Number=16.0.0.Final
 wget https://download.jboss.org/wildfly/$Version_Number/wildfly-$Version_Number.tar.gz -P /tmp
 sudo tar xf /tmp/wildfly-$Version_Number.tar.gz -C /opt/
 sudo ln -s /opt/wildfly-$Version_Number /opt/wildfly
 sudo chown -RH wildfly: /opt/wildfly
 sudo mkdir -p /etc/wildfly

