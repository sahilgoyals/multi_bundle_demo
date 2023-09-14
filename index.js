import {AppRegistry} from 'react-native';

const CommonApp = () => null;

if (__DEV__) {
  require('./src/views/appA');
  require('./src/views/appB');
} else {
  AppRegistry.registerComponent('CommonApp', () => CommonApp);
}

