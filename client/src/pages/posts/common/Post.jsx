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
    <section
      className="flex flex-col p-2.5 m-1.5 border-solid border border-slate-500 hover:cursor-pointer hover:bg-slate-200/30"
      onClick={postReadHandler}
      aria-hidden="true"
    >
      <img
        className="border-solid border border-zinc-300 w-full h-full md:h-350"
        src={`http://211.41.205.19:8080/board/image/${items.imageId[0]}`}
        alt="img"
      />
      <p className="text-start text-2xl">{items.title}</p>
      <div className="flex justify-between">
        <span>👤 작성자</span>
        <button>❤️ {Math.floor(Math.random() * 100) + 1}</button>
      </div>
    </section>
  );
}
