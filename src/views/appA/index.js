import React, {useEffect} from 'react';
import {AppRegistry} from 'react-native';
import * as Sentry from '@sentry/react-native';
import AppA from './AppA';

function AppAMain() {
  useEffect(() => {
    Sentry.init({
      dsn: 'https://1f1780d174c2e9412f15da802bb637c1@o4505861388173312.ingest.sentry.io/4505861389352960',
      dist: '1',
      // Set tracesSampleRate to 1.0 to capture 100%
      // of transactions for performance monitoring.
      // We recommend adjusting this value in production
      tracesSampleRate: 1.0,
    });
  }, []);

  return <AppA />;
}

AppRegistry.registerComponent('appARegistry', () => Sentry.wrap(AppAMain));
