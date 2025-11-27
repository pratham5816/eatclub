import React, { useState, useEffect } from 'react';
import { Box, Typography, CircularProgress, Alert } from '@mui/material';

interface AnalyticsData {
  totalEntries: number;
  successRate: number;
  averageWaitTime: number;
  lastUpdated: string;
}

const Analytics: React.FC = () => {
  const [data, setData] = useState<AnalyticsData | null>(null);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  // Function to fetch analytics data
  const fetchAnalyticsData = async (): Promise<void> => {
    try {
      setLoading(true);
      setError(null);
      
      // TODO: Replace with your actual API endpoint
      // const response = await fetch('http://localhost:8080/api/analytics');
      // const result = await response.json();
      
      // Simulated data for now
      await new Promise(resolve => setTimeout(resolve, 1000));
      
      const computedData = computeAnalytics();
      setData(computedData);
    } catch (err) {
      setError('Failed to fetch analytics data');
      console.error('Error fetching analytics:', err);
    } finally {
      setLoading(false);
    }
  };

  // Function to compute analytics from raw data
  const computeAnalytics = (): AnalyticsData => {
    // TODO: Add your actual computation logic here
    // This could include aggregating data, calculating averages, etc.
    
    return {
      totalEntries: 42,
      successRate: 85.5,
      averageWaitTime: 12.3,
      lastUpdated: new Date().toLocaleString(),
    };
  };

  // Fetch data on component mount
  useEffect(() => {
    fetchAnalyticsData();
    
    // Optional: Set up polling to refresh data periodically
    const interval = setInterval(() => {
      fetchAnalyticsData();
    }, 60000); // Refresh every 60 seconds

    return () => clearInterval(interval);
  }, []);

  // Function to manually refresh data
  const handleRefresh = (): void => {
    fetchAnalyticsData();
  };

  if (loading) {
    return (
      <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: 100 }}>
        <CircularProgress size={24} />
      </Box>
    );
  }

  if (error) {
    return (
      <Alert severity="error" onClose={() => setError(null)}>
        {error}
      </Alert>
    );
  }

  if (!data) {
    return (
      <Typography variant="body2" color="text.secondary">
        No analytics data available
      </Typography>
    );
  }

  return (
    <Box sx={{ display: 'flex', flexDirection: 'column', gap: 1.5 }}>
      <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
        <Typography variant="body2" color="text.secondary">
          View insights about your automation status
        </Typography>
        <Typography 
          variant="caption" 
          sx={{ cursor: 'pointer', color: 'primary.main', '&:hover': { textDecoration: 'underline' } }}
          onClick={handleRefresh}
        >
          Refresh
        </Typography>
      </Box>
      
      <Box sx={{ display: 'flex', flexDirection: 'column', gap: 1 }}>
        <Box sx={{ display: 'flex', justifyContent: 'space-between' }}>
          <Typography variant="body2" fontWeight="medium">
            Total Entries:
          </Typography>
          <Typography variant="body2" color="primary">
            {data.totalEntries}
          </Typography>
        </Box>
        
        <Box sx={{ display: 'flex', justifyContent: 'space-between' }}>
          <Typography variant="body2" fontWeight="medium">
            Success Rate:
          </Typography>
          <Typography variant="body2" color="success.main">
            {data.successRate}%
          </Typography>
        </Box>
        
        <Box sx={{ display: 'flex', justifyContent: 'space-between' }}>
          <Typography variant="body2" fontWeight="medium">
            Avg Wait Time:
          </Typography>
          <Typography variant="body2">
            {data.averageWaitTime}s
          </Typography>
        </Box>
      </Box>
      
      <Typography variant="caption" color="text.secondary" sx={{ mt: 1 }}>
        Last updated: {data.lastUpdated}
      </Typography>
    </Box>
  );
};

export default Analytics;
