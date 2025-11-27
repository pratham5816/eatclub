import axios from "axios";
import { useEffect, useState } from "react";

interface ApiResponse {
  success: boolean;
  mysid: string | null;
  message: string;
}

function DemoApi() {
  const [data, setData] = useState<ApiResponse | null>(null);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    fetchMySid();
  }, []);

  const fetchMySid = async () => {
    try {
      setLoading(true);
      setError(null);

      console.log("Fetching custom.mysid from backend...");

      const response = await axios.get<ApiResponse>(
        "http://localhost:8080/api/demo/get-mysid"
      );

      console.log("Response:", response.data);
      setData(response.data);
    } catch (err: any) {
      console.error("Error:", err);
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ padding: "20px", fontFamily: "Arial, sans-serif" }}>
      <h2>🔍 Demo API - Get Custom MySid</h2>

      {loading && (
        <p style={{ color: "blue" }}>⏳ Loading custom.mysid...</p>
      )}

      {error && (
        <div
          style={{
            backgroundColor: "#ffebee",
            border: "1px solid #f44336",
            padding: "15px",
            borderRadius: "5px",
            marginTop: "10px",
          }}
        >
          <strong>❌ Error:</strong> {error}
        </div>
      )}

      {!loading && data && (
        <div
          style={{
            backgroundColor: data.success ? "#e8f5e9" : "#fff3e0",
            border: `1px solid ${data.success ? "#4caf50" : "#ff9800"}`,
            padding: "15px",
            borderRadius: "5px",
            marginTop: "10px",
          }}
        >
          <p>
            <strong>Status:</strong>{" "}
            {data.success ? "✅ Success" : "⚠️ Warning"}
          </p>
          <p>
            <strong>Message:</strong> {data.message}
          </p>
          <p>
            <strong>Custom MySid:</strong>{" "}
            <code
              style={{
                backgroundColor: "#f5f5f5",
                padding: "5px 10px",
                borderRadius: "3px",
              }}
            >
              {data.mysid || "null"}
            </code>
          </p>
        </div>
      )}

      <button
        onClick={fetchMySid}
        disabled={loading}
        style={{
          marginTop: "20px",
          padding: "10px 20px",
          backgroundColor: loading ? "#ccc" : "#2196F3",
          color: "white",
          border: "none",
          borderRadius: "5px",
          cursor: loading ? "not-allowed" : "pointer",
          fontSize: "16px",
        }}
      >
        🔄 Refresh
      </button>

      <div
        style={{
          marginTop: "30px",
          padding: "15px",
          backgroundColor: "#f5f5f5",
          borderRadius: "5px",
        }}
      >
        <h3>📝 How to Set Environment Variable:</h3>
        <ol>
          <li>
            <strong>In Terminal (before starting backend):</strong>
            <pre
              style={{
                backgroundColor: "#263238",
                color: "#aed581",
                padding: "10px",
                borderRadius: "5px",
                overflow: "auto",
              }}
            >
              export TWILLIO_SID="your-sid-value-here"
            </pre>
          </li>
          <li>
            <strong>Or add to .env file:</strong>
            <pre
              style={{
                backgroundColor: "#263238",
                color: "#aed581",
                padding: "10px",
                borderRadius: "5px",
                overflow: "auto",
              }}
            >
              TWILLIO_SID=your-sid-value-here
            </pre>
          </li>
          <li>
            <strong>Then restart Spring Boot app</strong>
          </li>
        </ol>
      </div>
    </div>
  );
}

export default DemoApi;
