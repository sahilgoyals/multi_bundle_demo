import {AppRegistry} from 'react-native';

const CommonApp = () => null;

if (__DEV__) {
  require('./packages/appA');
  require('./packages/appB');
} else {
  AppRegistry.registerComponent('CommonApp', () => CommonApp);
}
