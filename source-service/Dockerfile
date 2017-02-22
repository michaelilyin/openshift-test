FROM jboss/wildfly

COPY ./build/libs/*.war /opt/jboss/wildfly/standalone/deployments

RUN /opt/jboss/wildfly/bin/add-user.sh admin password

CMD /opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0
