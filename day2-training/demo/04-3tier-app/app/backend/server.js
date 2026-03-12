const express = require('express');
const { Pool } = require('pg');
const cors = require('cors');

const app = express();
const port = process.env.PORT || 3000;

app.use(cors());
app.use(express.json());

// Database connection setup
const pool = new Pool({
  user: process.env.DB_USER || 'postgres',
  host: process.env.DB_HOST || 'localhost',
  database: process.env.DB_NAME || 'demodb',
  password: process.env.DB_PASSWORD || 'secret',
  port: process.env.DB_PORT || 5432,
});

// Initialize database table if it doesn't exist
const initDb = async () => {
  try {
    const client = await pool.connect();
    await client.query(`
      CREATE TABLE IF NOT EXISTS messages (
        id SERIAL PRIMARY KEY,
        content VARCHAR(255) NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
      );
    `);
    
    // Insert some dummy data if empty
    const res = await client.query('SELECT COUNT(*) FROM messages');
    if (parseInt(res.rows[0].count) === 0) {
      await client.query(`
        INSERT INTO messages (content) VALUES
          ('Hello from the database!'),
          ('Kubernetes networking is working!'),
          ('Minikube is awesome.')
      `);
      console.log('Inserted seed data into database.');
    }
    client.release();
    console.log('Database initialized successfully.');
  } catch (err) {
    console.error('Error initializing database:', err);
    // Continue running anyway so we can see the app running (with DB errors)
  }
};

// Simple route to get data
app.get('/api/data', async (req, res) => {
  try {
    const result = await pool.query('SELECT * FROM messages ORDER BY created_at DESC');
    res.json({
      status: 'success',
      source: 'backend-api',
      data: result.rows
    });
  } catch (err) {
    console.error('Database query error:', err);
    res.status(500).json({ error: 'Failed to fetch data from database' });
  }
});

app.get('/api/health', (req, res) => {
  res.status(200).json({ status: 'healthy' });
});

// Start server and initialize DB
app.listen(port, () => {
  console.log(`Backend API listening on port ${port}`);
  
  // Wait a few seconds before trying to connect to DB to allow DB pod to start
  setTimeout(initDb, 3000);
});
