// /* eslint-disable max-len */
// import 'react-native-gesture-handler'
// import { AppRegistry } from 'react-native'
// import * as Sentry from '@sentry/react-native'
// import NewRelic from 'newrelic-react-native-agent'
// import CodePush from 'react-native-code-push'
// import HousingApp from './src/App'
// import { name as appName } from './app.json'
// import { initializeBugsnag } from './src/utils/initializeBugsnag'
// import { version } from './package.json'
// import { ConfigHelpers } from './src/utils/ConfigHelpers'
// import { isIos } from './src/utils/TypeChecker'

// const codePushOptions = { checkFrequency: CodePush.CheckFrequency.ON_APP_START }

// let appToken

// if (isIos) {
//   appToken = ConfigHelpers.getNewRelicIosToken()
// } else {
//   appToken = ConfigHelpers.getNewRelicAndroidToken()
// }

// if (!__DEV__) {
//   initializeBugsnag()
// }

// if (__DEV__) {
//   AppRegistry.registerComponent(appName, () => Sentry.wrap(CodePush(codePushOptions)(HousingApp)))
// } else {
//   const agentConfiguration = {

//     // Android Specific
//     // Optional:Enable or disable collection of event data.
//     analyticsEventEnabled: true,

//     // Optional:Enable or disable crash reporting.
//     crashReportingEnabled: true,

//     // Optional:Enable or disable interaction tracing. Trace instrumentation still occurs, but no traces are harvested. This will disable default and custom interactions.
//     interactionTracingEnabled: true,

//     // Optional:Enable or disable reporting successful HTTP requests to the MobileRequest event type.
//     networkRequestEnabled: true,

//     // Optional:Enable or disable reporting network and HTTP request errors to the MobileRequestError event type.
//     networkErrorRequestEnabled: true,

//     // Optional:Enable or disable capture of HTTP response bodies for HTTP error traces, and MobileRequestError events.
//     httpRequestBodyCaptureEnabled: true,

//     // Optional:Enable or disable agent logging.
//     loggingEnabled: true,

//     // Optional:Specifies the log level. Omit this field for the default log level.
//     // Options include: ERROR (least verbose), WARNING, INFO, VERBOSE, AUDIT (most verbose).
//     logLevel: NewRelic.LogLevel.INFO,

//     // iOS Specific
//     // Optional:Enable/Disable automatic instrumentation of WebViews
//     webViewInstrumentation: true,

//     // Optional:Set a specific collector address for sending data. Omit this field for default address.
//     collectorAddress: '',

//     // Optional:Set a specific crash collector address for sending crashes. Omit this field for default address.
//     crashCollectorAddress: ''
//   }

//   NewRelic.startAgent(appToken, agentConfiguration)
//   NewRelic.setJSAppVersion(version)
//   AppRegistry.registerComponent(appName, () => Sentry.wrap(CodePush(codePushOptions)(HousingApp)))
// }

import { AppRegistry } from 'react-native'

const CommonApp = () => (
  null
)

if (__DEV__) {
  // require('../../index')
//   require('../../packages/seller')
//   require('../../packages/news')
//   require('../../packages/calc')
//   require('../../packages/Edge')
  // AppRegistry.registerComponent('housing', () => HousingApp)
} else {
  AppRegistry.registerComponent('CommonApp', () => CommonApp)
}
