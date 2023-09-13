import React from 'react';
import {AppRegistry} from 'react-native';
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
  const navigateToAppA = () => {
    console.log('Sahil:navigateToAppA');
  };

  const throwErrorInAppB = () => {
    console.log('Sahil:throwErrorInAppB');
    throw new Error('Sahil:throwErrorInAppB');
  };

  const navigateBack = () => {
    console.log('Sahil:navigateBack');
  };

  return (
    <View style={styles.container}>
      <TouchableOpacity onPress={() => navigateBack()} style={styles.back}>
        <Text>Back</Text>
      </TouchableOpacity>
      <View style={styles.header}>
        <Text>Welcome to App B</Text>
      </View>
      <View style={styles.buttonContainer}>
        <TouchableOpacity
          style={styles.button}
          onPress={() => navigateToAppA()}>
          <Text>Navigate to App A</Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={styles.button}
          onPress={() => throwErrorInAppB()}>
          <Text>Throw error on App B</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}

AppRegistry.registerComponent('AppB', () => App);
