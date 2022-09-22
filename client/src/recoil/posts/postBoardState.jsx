import { atom } from 'recoil';
import { recoilPersist } from 'recoil-persist';

const { persistAtom } = recoilPersist();

export const postBoardState = atom({
  key: 'postBoardState',
  default: 0,
  effects_UNSTABLE: [persistAtom],
});
