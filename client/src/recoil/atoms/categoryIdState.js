import { atom } from 'recoil';

import { recoilPersist } from 'recoil-persist';

const { persistAtom } = recoilPersist();

export const categoryIdState = atom({
  key: 'categoryIdState',
  default: 0,
  effects_UNSTABLE: [persistAtom],
});
