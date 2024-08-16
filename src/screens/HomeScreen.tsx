import React from 'react';
import {View, Text} from 'react-native';
import styles from './HomeScreenStyles';

const HomeScreen: React.FC = () => {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Welcome to Healthcare App</Text>
    </View>
  );
};

export default HomeScreen;
