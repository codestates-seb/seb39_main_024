export default function Pagination({ posts, currentPage, setCurrentPage }) {
  return (
    <div className="flex justify-center items-center my-8">
      <button
        className="sm:text-xl text-2xl mx-3 px-3 hover:bg-slate-200 hover:rounded cursor-pointer disabled:bg-white disabled:cursor-auto"
        disabled={currentPage < 1}
        onClick={() => setCurrentPage((prev) => prev - 1)}
      >
        &#8249;
      </button>
      <strong className="text-2xl">{currentPage + 1}</strong>
      <button
        className="sm:text-xl text-2xl mx-3 px-3 hover:bg-slate-200 hover:rounded cursor-pointer disabled:bg-white disabled:cursor-auto"
        disabled={posts.length < 4}
        onClick={() => setCurrentPage((prev) => prev + 1)}
      >
        &#8250;
      </button>
    </div>
  );
}
