package nico.dw

import com.yammer.dropwizard.Service
import com.yammer.dropwizard.assets.AssetsBundle
import com.yammer.dropwizard.config.Bootstrap
import com.yammer.dropwizard.config.Environment
import com.yammer.dropwizard.db.DatabaseConfiguration
import com.yammer.dropwizard.hibernate.HibernateBundle
import com.yammer.dropwizard.migrations.MigrationsBundle
import nico.dw.resources.HelloWorldResource

class FirstServiceService extends Service<FirstServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new FirstServiceService().run(args)
    }

    HibernateBundle<FirstServiceConfiguration> hibernateBundle =
        new HibernateBundle<FirstServiceConfiguration>([]) {
            @Override
            public DatabaseConfiguration getDatabaseConfiguration(FirstServiceConfiguration configuration) {
                return configuration.databaseConfiguration
            }
        }

    MigrationsBundle<FirstServiceConfiguration> migrationsBundle =
        new MigrationsBundle<FirstServiceConfiguration>() {
            @Override
            public DatabaseConfiguration getDatabaseConfiguration(FirstServiceConfiguration configuration) {
                return configuration.databaseConfiguration
            }
        }

    AssetsBundle assetsBundle = new AssetsBundle()

    @Override
    public void initialize(Bootstrap<FirstServiceConfiguration> bootstrap) {
        bootstrap.with {
            name = 'FirstService'
            addBundle migrationsBundle
            addBundle hibernateBundle
        }
    }

    @Override
    public void run(FirstServiceConfiguration configuration,
                    Environment environment) throws ClassNotFoundException {

            environment.addResource(new HelloWorldResource("Hello %s", "Nicolas"))
    }
}
