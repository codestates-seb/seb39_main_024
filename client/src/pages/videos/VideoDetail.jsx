export default function VideoDetail({ video, video: { snippet } }) {
  // console.log(snippet.description);
  return (
    <section>
      <iframe
        title="ytplayer"
        type="text/html"
        width="100%"
        height="500px"
        src={`https://www.youtube.com/embed/${video.id}`}
        frameBorder="0"
        allowFullScreen
      ></iframe>
      <h2>{snippet.title}</h2>
      <h3>{snippet.channelTitle}</h3>
      <pre>{snippet.description}</pre>
    </section>
  );
}
