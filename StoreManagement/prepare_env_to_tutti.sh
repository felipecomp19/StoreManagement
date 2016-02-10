cp src/main/resources/hibernate_tutti.cfg.xml src/main/resources/hibernate.cfg.xml
echo 'hibernate_tutti.cfg.xml --- > hibernate.cfg.xml'

cp src/main/resources/log4j_prod.xml src/main/resources/log4j.xml
echo 'log4j_prod.xml --- > log4j.xml'

cp src/main/webapp/WEB-INF/spring/root-context_tutti.xml src/main/webapp/WEB-INF/spring/root-context.xml
echo 'root-context_tutti.xml --- > root-context.xml'

cp src/main/webapp/WEB-INF/spring/appServlet/servlet-context_sm.xml src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml
echo 'servlet-context_sm.xml --- > servlet-context.xml'

cp src/main/webapp/WEB-INF/jboss-web_tutti.xml src/main/webapp/WEB-INF/jboss-web.xml
echo 'jboss-web_tutti.xml --- > jboss-web.xml'
echo 'DONE!'