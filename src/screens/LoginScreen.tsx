import React, {useState} from 'react';
import {View, Text, TextInput, Button, Alert} from 'react-native';
import {NativeModules} from 'react-native';
import validator from 'validator';
import styles from './LoginScreenStyles';

const {HawcxModule} = NativeModules;

interface LoginScreenProps {
  navigation: any;
}

const LoginScreen: React.FC<LoginScreenProps> = ({navigation}) => {
  const [email, setEmail] = useState<string>('');

  const handleLogin = () => {
    if (validator.isEmail(email) === false) {
      Alert.alert('Invalid email address');
      return;
    }

    HawcxModule.login(email)
      .then(() => {
        console.log('success');
      })
      .catch((err: string) => {
        Alert.alert('Login Failed', err.toString());
      });
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Login</Text>
      <TextInput
        style={styles.input}
        placeholder="Email"
        value={email}
        onChangeText={setEmail}
      />
      <Button title="Login" onPress={handleLogin} />
    </View>
  );
};

export default LoginScreen;
