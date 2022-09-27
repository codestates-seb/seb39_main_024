export default function Comment({ items }) {
  return (
    <section className="bg-white">
      <div className="flex justify-between">
        <div>
          <span className="mr-2 ml-1">👤 작성자</span>
          <span className="mr-2">&#124;</span>
          <span className="ml-1">{items.createdAt}</span>
        </div>
        <div>
          <button className="mr-1">수정</button>
          <button className="mr-1">삭제</button>
        </div>
      </div>
      <p className="ml-1">{items.content}</p>
    </section>
  );
}
