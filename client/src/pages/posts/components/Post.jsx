import { useNavigate } from 'react-router-dom';
import { useSetRecoilState } from 'recoil';

import { boardIdState } from '../../../recoil/posts/atoms/boardIdState';

export default function Post({ items }) {
  const navigation = useNavigate();

  const setPostBoard = useSetRecoilState(boardIdState);

  const postReadHandler = () => {
    setPostBoard(items.boardId);
    navigation('/posts/read');
  };

  return (
    <div className="flex flex-col py-5 px-2.5">
      <div className="text-center border-solid border border-zinc-300 p-7 w-52 h-52 bg-white">
        사진
      </div>
      <button className="text-start" onClick={postReadHandler}>
        {items.title}
      </button>
      <div className="flex justify-between">
        <span>👤 작성자</span>
        <button>❤️ {Math.floor(Math.random() * 100) + 1}</button>
      </div>
    </div>
  );
}
