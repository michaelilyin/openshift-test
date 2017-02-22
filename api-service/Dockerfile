FROM jboss/wildfly

RUN /opt/jboss/wildfly/bin/add-user.sh admin password

RUN chmod -R o+rw /opt/jboss

COPY ./build/libs/*.war /opt/jboss/wildfly/standalone/deployments

CMD /opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0
