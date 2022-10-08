import { atom } from 'recoil';
import { recoilPersist } from 'recoil-persist';

const { persistAtom } = recoilPersist();

export const authorizationState = atom({
  key: 'authorizationState',
  default: '',
  effects_UNSTABLE: [persistAtom],
});
