package com.viseo.starter.configuration;

/**
 */
public interface Profiles {


    // - no profile
    //   - default configuration local run/dev
    // - 'srv' profile
    //   - should be used on servers (production, preprod, ...).
    //   - application-srv.yml included in this project provides common configuration for servers
    //   - on each server, we can add an environment specific application-srv.properties|yml in /opt/mear

    String SRV = "srv";

    String DEV = "dev";

}
