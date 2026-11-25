const { Eureka } = require('eureka-js-client');

/**
 * Creates a Eureka client instance for the MEETING microservice.
 * @param {number|string} port - Listening port of this instance.
 * @returns {Eureka}
 */
function createEurekaClient(port) {
  const numericPort = Number(port);
  const hostName = process.env.EUREKA_INSTANCE_HOST || 'localhost';
  const ipAddr = process.env.EUREKA_INSTANCE_IP || '127.0.0.1';
  const eurekaHost = process.env.EUREKA_HOST || 'localhost';
  const eurekaPort = Number(process.env.EUREKA_PORT) || 8761;

  return new Eureka({
    instance: {
      app: 'MEETING',
      instanceId: `${hostName}:meeting:${numericPort}`,
      hostName,
      ipAddr,
      statusPageUrl: `http://${hostName}:${numericPort}/swagger-ui`,
      healthCheckUrl: `http://${hostName}:${numericPort}/api/meetings/hello`,
      homePageUrl: `http://${hostName}:${numericPort}/`,
      port: {
        $: numericPort,
        '@enabled': true,
      },
      vipAddress: 'MEETING',
      dataCenterInfo: {
        '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
        name: 'MyOwn',
      },
    },
    eureka: {
      host: eurekaHost,
      port: eurekaPort,
      servicePath: '/eureka/apps/',
      maxRetries: 10,
      requestRetryDelay: 2000,
    },
  });
}

module.exports = { createEurekaClient };

