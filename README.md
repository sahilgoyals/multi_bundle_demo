Issue Description :

In our Native App, we have adopted an architecture where three React Native bundles are dynamically loaded into the application based on specific requirements. This approach is essential for achieving low latency in bundle loading, ensuring a smooth user experience.
However, our problem lies in error tracing. Sentry's React Native SDK allows us to upload a single sourcemap and bundle to map error line numbers. But in our case, we have three separate bundles, each with its own sourcemap. We are currently unable to find a way to upload multiple sourcemaps, similar to how it's done on the web platform.

Key Details:

- Our Native App consists of three React Native bundles that run within a single Android/iOS app. Each bundle is initialized with its own
sentry.init({ dist: /respective_dist/, ...other_params })
- The main Android app holds the DSN keys and passes them to the React Native bundles.
- While attempting to upload source-map files, we utilised different dist values for each of the bundles, allowing us to upload multiple source-maps for the same version of the app.

Steps To Run:

- Add your token, project, org in .sentryclirc file
- Update DSN_HERE in [appA index.js](https://github.com/sahilgoyals/housing_demo/blob/a1f1dbaf2adbc5c3b085d2aca8c239705681189e/src/views/appA/index.js) file and [appB index.js](https://github.com/sahilgoyals/housing_demo/blob/a1f1dbaf2adbc5c3b085d2aca8c239705681189e/src/views/appB/index.js) file
- Run commands
  1. npm run bundleAndroid
  2. npm run android --no-packager

<img alt="image" src="/image.png">