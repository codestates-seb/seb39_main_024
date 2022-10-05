import { useSetRecoilState } from 'recoil';
import { isLoginState } from '../../recoil/atoms/isLoginState';

export default function Logout() {
  function onClick() {
    const setIsLogin = useSetRecoilState(isLoginState);
    setIsLogin(false);
  }
  return <button onClick={onClick}>Logout</button>;
}
