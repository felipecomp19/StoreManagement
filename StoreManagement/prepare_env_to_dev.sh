cp src/main/resources/hibernate_desenv.cfg.xml src/main/resources/hibernate.cfg.xml
echo 'hibernate_desenv.cfg.xml --- > hibernate.cfg.xml'

cp src/main/resources/log4j_desenv.xml src/main/resources/log4j.xml
echo 'log4j_desenv.xml --- > log4j.xml'

cp src/main/webapp/WEB-INF/spring/root-context_desenv.xml src/main/webapp/WEB-INF/spring/root-context.xml
echo 'root-context_desenv.xml --- > root-context.xml'

cp src/main/webapp/WEB-INF/spring/appServlet/servlet-context_desenv.xml src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml
echo 'servlet-context_desenv.xml --- > servlet-context.xml'

cp src/main/webapp/WEB-INF/jboss-web_desenv.xml src/main/webapp/WEB-INF/jboss-web.xml
echo 'jboss-web_desenv.xml --- > jboss-web.xml'
echo 'DONE!'
