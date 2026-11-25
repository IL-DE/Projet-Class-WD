const app = require('./app');
const { createEurekaClient } = require('./config/eureka');

const PORT = Number(process.env.PORT) || 8083;
const eurekaClient = createEurekaClient(PORT);

const server = app.listen(PORT, () => {
  console.log(`meeting microservice running on http://localhost:${PORT}`);
  console.log(`Swagger UI: http://localhost:${PORT}/swagger-ui`);

  eurekaClient.start((error) => {
    if (error) {
      console.error('Eureka registration error:', error);
    } else {
      console.log(`Registered MEETING on Eureka (port ${PORT})`);
    }
  });
});

function shutdown() {
  eurekaClient.stop(() => {
    server.close(() => process.exit(0));
  });
}

process.on('SIGINT', shutdown);
process.on('SIGTERM', shutdown);
