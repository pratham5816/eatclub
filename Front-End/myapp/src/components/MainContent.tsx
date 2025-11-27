import React, { useState } from 'react';
import { Box, Container, Paper, Typography, TextField, Button, IconButton } from '@mui/material';
import GitHubIcon from '@mui/icons-material/GitHub';
import LinkedInIcon from '@mui/icons-material/LinkedIn';
import axios from 'axios';
import Eatclub from './Eatclub';

const MainContent: React.FC = () => {
  const [city, setCity] = useState('');
  const [phone, setPhone] = useState('');
  const [outletName, setOutletName] = useState('');
  const [loading, setLoading] = useState(false);
  const [output, setOutput] = useState('');
  const [outputType, setOutputType] = useState<'success' | 'error' | ''>('');

  const handleSubmit = async () => {
    // Clear previous output
    setOutput('');
    setOutputType('');

    if(city.trim() === ""){
      setOutput("⚠️ Please enter a city name");
      setOutputType('error');
      return;
    }

    // Regex validation for city (only letters and spaces, no numbers or special chars)
    const cityRegex = /^[a-zA-Z\s]+$/;
    if(!cityRegex.test(city.trim())){
      setOutput("⚠️ City name should only contain letters and spaces");
      setOutputType('error');
      return;
    }

    if(phone.trim() === ""){
      setOutput("⚠️ Please enter a phone number");
      setOutputType('error');
      return;
    }

    // Regex validation for phone number (10 digits or with country code)
    const phoneRegex = /^(\+?\d{1,3}[-.\s]?)?\d{10}$/;
    if(!phoneRegex.test(phone.trim())){
      setOutput("⚠️ Please enter a valid phone number (10 digits)");
      setOutputType('error');
      return;
    }

    if(outletName.trim() === ""){
      setOutput("⚠️ Please enter an outlet name");
      setOutputType('error');
      return;
    }

    setLoading(true);
    try {
      // Send data to backend (no response needed)
      await axios.post('http://localhost:8080/send-user-data', {
        city,
        phone,
        outletName
      });
      
      // Show success message
      setOutput('Request sent successfully! You will receive an SMS when the restaurant queue clears.');
      setOutputType('success');
      
      // Clear form so user can submit another request
      setCity('');
      setPhone('');
      setOutletName('');
    } catch (error: any) {
      console.error('Error:', error);
      
      // Better error messages to help debug
      if (error.response) {
        // Backend responded but with error (4xx or 5xx status)
        setOutput(`Server error: ${error.response.data || error.response.statusText}`);
      } else if (error.request) {
        // Request sent but no response received (backend not running)
        setOutput('Cannot reach server. Make sure backend is running on http://localhost:8080');
      } else {
        // Something else went wrong
        setOutput('Something went wrong! Please try again.');
      }
      setOutputType('error');
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
      <Box sx={{ display: 'flex', flexDirection: 'column', gap: 3 }}>
        {/* Welcome Section */}
        <Paper
          sx={{
            p: 3,
            display: 'flex',
            flexDirection: 'column',
            backgroundColor: '#f5f5f5',
          }}
        >
          <Typography variant="h4" gutterBottom>
            Welcome to EatClub Automation
          </Typography>
          <Typography variant="body1" color="text.secondary">
            This App will help to Bypass the Manual waiting queue of the existing EatClub application. "Kitchen Full Come Back Later". <br/>
            Enter the required details to begin.
          </Typography>
        </Paper>
           {/* Disclaimer */}
        <Paper
          sx={{
            mt: 4,
            p: 3,
            backgroundColor: '#fff3cd',
            borderLeft: '4px solid #ffc107',
          }}
        >
          <Typography variant="h6" gutterBottom sx={{ color: '#856404', fontWeight: 600 }}>
            ⚠️ Disclaimer
          </Typography>
          <Typography variant="body2" sx={{ color: '#856404', lineHeight: 1.8 }}>
            This is an independent project developed for educational and automation purposes only. 
            We are <strong>NOT affiliated, associated, authorized, endorsed by, or in any way officially 
            connected with EatClub</strong> or any of its subsidiaries or affiliates. All product names, 
            logos, and brands are property of their respective owners. Use this tool at your own discretion.
          </Typography>
        </Paper>

        {/* City Input Box */}
        <Paper
          sx={{
            p: 3,
            display: 'flex',
            flexDirection: 'column',
            gap: 2,
            transition: 'transform 0.2s',
            '&:hover': {
              transform: 'scale(1.02)',
              boxShadow: 6,
            },
          }}
        >
          <Typography variant="h6" gutterBottom color="primary">
            Enter Details to Begin
          </Typography>
          
          <TextField
            label="Enter city"
            variant="outlined"
            fullWidth
            value={city}
            onChange={(e) => setCity(e.target.value)}
            placeholder="e.g., Mumbai, Bangalore, Delhi"
            helperText="Enter city name (letters and spaces only)"
          />
          
          <TextField id="txtPhone"
            label="Enter phone number"
            variant="outlined"
            fullWidth
            value={phone}
            onChange={(e) => setPhone(e.target.value)}
            placeholder="e.g., 9876543210 or +91 9876543210"
            helperText="Enter 10-digit phone number with or without country code"
          />
          
          <TextField
            label="Enter outlet name"
            variant="outlined"
            fullWidth
            value={outletName}
            onChange={(e) => setOutletName(e.target.value)}
            helperText="Enter outlet id (Wakad near jardin oulet Id is 46)"
          />

          <Button
            variant="contained"
            color="primary"
            size="large"
            onClick={handleSubmit}
            disabled={loading}
            sx={{ mt: 2 }}
          >
            {loading ? 'Submitting...' : 'Submit'}
          </Button>

          {/* Output Container */}
          {output && (
            <Paper
              sx={{
                p: 2,
                mt: 2,
                backgroundColor: outputType === 'success' ? '#d4edda' : '#f8d7da',
                borderLeft: `4px solid ${outputType === 'success' ? '#28a745' : '#dc3545'}`,
              }}
            >
              <Typography
                variant="body1"
                sx={{
                  color: outputType === 'success' ? '#155724' : '#721c24',
                  fontWeight: 500,
                }}
              >
                {output}
              </Typography>
            </Paper>
          )}
        </Paper>

        {/* Content Section
        <Paper sx={{ p: 3 }}>
          <Typography variant="h5" gutterBottom>
            Recent Activity
          </Typography>
          <Box sx={{ mt: 2 }}>
            <Typography variant="body1" paragraph>
              Start adding your meal entries to see your recent activity here.
            </Typography>
            <Typography variant="body2" color="text.secondary">
              Your dining journey begins now. Track, discover, and enjoy!
            </Typography>
          </Box>
        </Paper> */}
      </Box>

        <Eatclub/>

       
        {/* Footer */}
        <Box
          sx={{
            mt: 6,
            py: 3,
            textAlign: 'center',
            borderTop: '1px solid #e0e0e0',
          }}
        >
          <Typography variant="body2" color="text.secondary" gutterBottom>
            © 2025 EatClub Automation. All rights reserved.
          </Typography>
          <Typography variant="body2" color="text.secondary" gutterBottom>
            Developed by <strong>Pratham</strong>
          </Typography>
          
          {/* Social Icons */}
          <Box sx={{ mt: 2 }}>
            <IconButton
              aria-label="GitHub"
              color="primary"
              href="https://github.com/pratham5816"
              target="_blank"
              rel="noopener noreferrer"
              sx={{ mx: 1 }}
            >
              <GitHubIcon />
            </IconButton>
            <IconButton
              aria-label="LinkedIn"
              color="primary"
              href="https://www.linkedin.com/in/pratham-kumar-taheem-530a18280/"
              target="_blank"
              rel="noopener noreferrer"
              sx={{ mx: 1 }}
            >
              <LinkedInIcon />
            </IconButton>
          </Box>
        </Box>
   
    </Container>
   
  );
};

export default MainContent;
