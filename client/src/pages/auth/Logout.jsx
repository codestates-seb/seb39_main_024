import { useSetRecoilState } from 'recoil';
import { loginState } from '../../recoil/atoms/loginState';

export default function Logout() {
  function onClick() {
    const setIsLogin = useSetRecoilState(loginState);
    setIsLogin(false);
  }
  return <button onClick={onClick}>Logout</button>;
}
