import { atom } from 'recoil';
import { recoilPersist } from 'recoil-persist';

const { persistAtom } = recoilPersist();

export const boardIdState = atom({
  key: 'boardIdState',
  default: 0,
  effects_UNSTABLE: [persistAtom],
});
