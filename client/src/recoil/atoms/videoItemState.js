import { atom } from 'recoil';
import { recoilPersist } from 'recoil-persist';

const { persistAtom } = recoilPersist();

//videoItem
export const videoItemState = atom({
  key: 'videoItemState',
  default: [],
  effects_UNSTABLE: [persistAtom],
});
