import React, {useEffect} from 'react';
import {AppRegistry} from 'react-native';
import * as Sentry from '@sentry/react-native';
import {StyleSheet, Text, TouchableOpacity, View} from 'react-native';

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  back: {
    flex: 0.03,
    marginLeft: 10,
    marginTop: 10,
    justifyContent: 'center',
  },
  header: {
    flex: 0.05,
    justifyContent: 'center',
    alignItems: 'center',
  },
  button: {
    flex: 0.1,
    marginVertical: 4,
    borderColor: 'orange',
    borderWidth: 2,
    justifyContent: 'center',
    alignItems: 'center',
    borderRadius: 20,
  },
  buttonContainer: {
    flex: 0.5,
    justifyContent: 'center',
  },
});

function App() {
  const throwErrorInAppA = () => {
    console.log('Sahil:throwErrorInAppA');
    throw new Error('Sahil:throwErrorInAppA');
  };

  useEffect(() => {
    Sentry.init({
      dsn: 'https://1f1780d174c2e9412f15da802bb637c1@o4505861388173312.ingest.sentry.io/4505861389352960',
      dist: "1",
      // Set tracesSampleRate to 1.0 to capture 100%
      // of transactions for performance monitoring.
      // We recommend adjusting this value in production
      tracesSampleRate: 1.0,
    });
  }, []);

  return (
    <View style={styles.container}>
      <View style={styles.header}>
        <Text>Welcome to App A</Text>
      </View>
      <View style={styles.buttonContainer}>
        <TouchableOpacity
          style={styles.button}
          onPress={() => throwErrorInAppA()}>
          <Text>Throw error on App A</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}

AppRegistry.registerComponent('appARegistry', () => Sentry.wrap(App));
