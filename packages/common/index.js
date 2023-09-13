import * as React from 'react'
const {AppRegistry} = require('react-native');
// import * as rnSentry from '@sentry/react-native'

const CommonApp = () => null

if (__DEV__) {
  require('../appA');
  require('../appB');
} else {
  AppRegistry.registerComponent('CommonApp', () => CommonApp);
}
