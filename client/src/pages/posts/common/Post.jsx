import { useNavigate } from 'react-router-dom';
import { useSetRecoilState } from 'recoil';

import { boardIdState } from '../../../recoil/atoms/boardIdState';

export default function Post({ items }) {
  const navigation = useNavigate();

  const setBoardId = useSetRecoilState(boardIdState);

  const postReadHandler = () => {
    setBoardId(items.boardId);
    navigation('/posts/read');
  };

  return (
    <section className="flex flex-col py-5 px-2.5">
      <img
        className="text-center border-solid border border-zinc-300 w-52 h-52"
        src={`http://211.41.205.19:8080/board/image/${items.imageId[0]}`}
        alt="img"
      />
      <button className="text-start" onClick={postReadHandler}>
        {items.title}
      </button>
      <div className="flex justify-between">
        <span>👤 작성자</span>
        <button>❤️ {Math.floor(Math.random() * 100) + 1}</button>
      </div>
    </section>
  );
}
